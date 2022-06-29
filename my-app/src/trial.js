
import './App.css';
//import React, { useState, useEffect } from 'react';
import JsonData from './output.json'

function trial() {
    const DisplayData=JsonData.map(
        (info)=>{
            return(
                <tr>
                    <td>{info.Installment_Number}</td>
                    <td>{info.Stage_Number}</td>
                    <td>{info.Installment_Due_Date}</td>
                    <td>{info.Installment_Amount}</td>
                    <td>{info.Interest_Rate}</td>
                    <td>{info.Current_Principal}</td>
                    <td>{info.Current_Interest}</td>
                    <td>{info.Current_Opening_Balance}</td>
                    <td>{info.Current_Closing_Balance}</td>

                </tr>
            )
        }
    )
 
    return(
        <div>
            <table className="table table-striped">
                <thead>
                    <tr>
                    <th scope="col">Installment No.</th>
            <th scope="col">Stage Number</th>
            <th scope="col">Installment Due Date</th>
            <th scope="col">Installment Amount</th>
            <th scope="col">Interest Rate</th>
            <th scope="col">Principal</th>
            <th scope="col">Interest</th>
            <th scope="col">Opening Balance</th>
            <th scope="col">CLosing Balance</th>

                    </tr>
                </thead>
                <tbody>
                 
                    
                    {DisplayData}
                    
                </tbody>
            </table>
             
        </div>
    )
}

export default trial;
