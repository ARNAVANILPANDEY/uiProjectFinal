function myFunction() {
     var finance=$("#finance").val();
     var repay_money=$("#repay_money").val();
     var amount=$("#amount").val();
     var Account_Open=$("#Account Open").val();
     var Segment_Number=$("#Segment Number").val();
     var Disbursement_Amount=$("#Disbursement Amount").val();
     var DisbursementDates=$("#fDisbursementDates").val();
     var tenure=$("#tenure").val();
     var rate=$("#rate").val();
     var conversion=$("#conversion").val();
     var amount=$("#amount").val();
     var dates=$("#dates").val();
     var repay_money=$("#repay_money").val();
     var installment_formula=$("#installment_formula").val();
     var installment_freq=$("#installment_freq").val();
    

     let text =[
        {
         "Installment No.": "1",
         "Stage Number": "1",
         "Installment Due Date": "10 Jul 22",
         "Installment Amount": "4754.420694549732",
         "Interest Rate": rate,
         "Component 1 (Principal)": "3758.256310988088",
         "Component 2 (Interest)": "996.1643835616438",
         "Opening Balance": "101000.0",
         "Closing Balance": "97241.74368901191"
        },]
        
const json = JSON.parse(JSON.stringify(text));
var fs = require('fs');
fs.writeFile('myjsonfile.json', JSON.stringify(json),function(err) {
	if(err) throw err;
	console.log("SUccess");
	}
);
}
