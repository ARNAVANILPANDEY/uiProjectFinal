package com.example.taskupdateui;

import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;



import java.math.RoundingMode;
import java.text.DecimalFormat;

@SpringBootApplication
@Controller
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/response")
public class TaskUpdateUiApplication {


    public double calculatePmt(double principle, double rate, long installPerYear, long years)
    {
        double PMT;
        double ratePerInstallment=rate/installPerYear;
        long totInstall=installPerYear*years;
        PMT=(principle*ratePerInstallment)/(1-Math.pow((1+ratePerInstallment),(-1*totInstall)));

        return PMT;
    }

    public Double calculatenterest(Double principle, Double rate, Long days)
    {
        Double simpleInterest = principle*rate*(1.0*days/365);

        return simpleInterest;
    }

    @PostMapping(
            value = "/postbody",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String home(@RequestBody HashMap<String,String> readData) throws IOException, org.json.simple.parser.ParseException, ParseException {
        /* *****************Reading**************************************** */
        //HashMap<String, String> readData;
        //readData = reader.reading();
        //readData
        //System.out.println("Read Data:\t"+readData);
        //jsonConv.writeInput(readData);
        //System.out.println(readData.get("Account Open Date"));
        /*Date*/String javaDate= /*DateUtil.getJavaDate(Double.parseDouble((String)*/ readData.get("Account Open Date");

        String sDate1=readData.get("Account Open Date");
        Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
       // SimpleDateFormat sdfNew=new SimpleDateFormat("dd MMM yy");





        //System.out.println("Hello");
        Double fees= Double.valueOf(readData.get("Finance Fees")),
                principleAmount=Double.valueOf(readData.get("Amount"))+fees,
                rateOfInterest=Double.valueOf(readData.get("Interest Rate"));
        Long installmentsPerYear = Long.valueOf(12), totYears= (Double.valueOf(readData.get("Tenure")).longValue())/12,
                totInstallments;
        Double pmt,lastPMT;
        totInstallments=installmentsPerYear*totYears;
        Double currPrincipal, currInterest, currOpenBal, currClosBal;



        pmt=calculatePmt(principleAmount,rateOfInterest,installmentsPerYear,totYears);
        System.out.println("PMT= "+pmt);

        /* ********************************Calender works**************************************** */
        SimpleDateFormat simpleformat2 = new SimpleDateFormat("dd MMM yy");
        String startDate=""+simpleformat2.format(date1);
        System.out.println(startDate);

        //Configuring the calendar instance to date given
        Calendar cal = Calendar.getInstance();
        Date par = null;
        try {
            par = simpleformat2.parse(startDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        cal.setTime(par);


        /* ********************************Preparing data************************************ */
        xlClass retClass =new xlClass();
        //Create the spreadsheet data for emi details
        for (int i=0;i<totInstallments;++i)
        {

            retClass.installmentNumber.addElement(Long.valueOf(i+1));
            retClass.stageNumber.addElement(Long.valueOf(1));
            SimpleDateFormat currSdf=new SimpleDateFormat("dd MMM yy");
            SimpleDateFormat prevSdf=new SimpleDateFormat("dd MMM yy");


            String currDate, prevDate;
            prevDate=prevSdf.format(cal.getTime());
            cal.add(Calendar.MONTH,1);
            currDate=currSdf.format(cal.getTime());
            retClass.installmentDueDate.addElement(currDate);

            retClass.interestRate.addElement(rateOfInterest);
            SimpleDateFormat tempSdf1=new SimpleDateFormat("dd MMM yy");
            SimpleDateFormat tempSdf2=new SimpleDateFormat("dd MMM yy");

            //Calculating number of days between current and previous month
            Long day= null;
            try {
                day =(tempSdf2.parse(currDate).getTime()-tempSdf1.parse(prevDate).getTime())/((1000*60*60*24));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            if (i==0)                   //First Installment
            {
                currOpenBal=principleAmount;
                retClass.installmentAmount.addElement(pmt);
                currInterest=calculatenterest(currOpenBal,rateOfInterest,day);
//            System.out.println(currInterest);
                currPrincipal=pmt-currInterest;
                currClosBal=currOpenBal-currPrincipal;

            }

            else if (i==totInstallments-1)      //Last Installment
            {
                currOpenBal=retClass.colCurrClosingBalance.lastElement();

                currInterest=calculatenterest(currOpenBal,rateOfInterest,day);
//            System.out.println(currInterest);
                retClass.installmentAmount.addElement(currOpenBal+currInterest);
                currPrincipal=retClass.installmentAmount.lastElement()-currInterest;
                currClosBal=currOpenBal-currPrincipal;


            }

            else            //Rest installments
            {
                retClass.installmentAmount.addElement(pmt);
                currOpenBal=retClass.colCurrClosingBalance.lastElement();
//                System.out.println("i=0     "+currDate+"   "+prevDate+"    "+day);
                currInterest=calculatenterest(currOpenBal,rateOfInterest,day);
//            System.out.println(currInterest);
                currPrincipal=pmt-currInterest;
                currClosBal=currOpenBal-currPrincipal;

            }


            DecimalFormat df=new DecimalFormat("0.00");
            df.setRoundingMode(RoundingMode.UP);
            retClass.colCurrPrincipal.addElement(currPrincipal);
            retClass.colCurrInterest.addElement(currInterest);
            retClass.colCurrOpeningBalance.addElement(currOpenBal);
            retClass.colCurrClosingBalance.addElement(currClosBal);


        }
        /* ***********************Writing to excel*************************************** */
        Vector<HashMap<String,String>> retVec=new Vector<HashMap<String,String>>();
        for (int i=0;i<retClass.installmentNumber.size();++i)
        {
//            DecimalFormat df = new DecimalFormat("#.####");
//            df.setRoundingMode(RoundingMode.);

            HashMap<String,String> tempMap =new HashMap<String,String>();
            System.out.print(retClass.installmentNumber.elementAt(i)+" ");
            tempMap.put("Installment_Number",String.valueOf(retClass.installmentNumber.elementAt(i)));
            System.out.print(" "+retClass.stageNumber.elementAt(i)+" ");
            tempMap.put("Stage_Number",String.valueOf(retClass.stageNumber.elementAt(i)));
            System.out.print(" "+retClass.installmentDueDate.elementAt(i)+" ");
            tempMap.put("Installment_Due_Date",String.valueOf(retClass.installmentDueDate.elementAt(i)));
            System.out.print(" "+retClass.installmentAmount.elementAt(i)+" ");
            tempMap.put("Installment_Amount",String.valueOf(retClass.installmentAmount.elementAt(i)));
            System.out.print(" "+retClass.interestRate.elementAt(i)+" ");
            tempMap.put("Interest_Rate",String.valueOf(retClass.interestRate.elementAt(i)));
            System.out.print(" "+retClass.colCurrPrincipal.elementAt(i)+" ");
            tempMap.put("Current_Principal",String.valueOf(retClass.colCurrPrincipal.elementAt(i)));
            System.out.print(" "+retClass.colCurrInterest.elementAt(i)+" ");
            tempMap.put("Current_Interest",String.valueOf(retClass.colCurrInterest.elementAt(i)));
            System.out.print(" "+retClass.colCurrOpeningBalance.elementAt(i)+" ");
            tempMap.put("Current_Opening_Balance",String.valueOf(retClass.colCurrOpeningBalance.elementAt(i)));
            System.out.print(" "+retClass.colCurrClosingBalance.elementAt(i)+" ");
            tempMap.put("Current_Closing_Balance",String.valueOf(retClass.colCurrClosingBalance.elementAt(i)));
            System.out.println("");
            retVec.addElement(tempMap);

        }
        System.out.println("Return Vector:\t"+retVec);
        //Call the write class
        jsonConv.writeOutput(retVec);
        write_existing.main(retVec,readData);



        return "final_page.html";
    }

    public static void main(String[] args) {
        SpringApplication.run(TaskUpdateUiApplication.class, args);
    }

}
