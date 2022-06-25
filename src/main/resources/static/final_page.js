fetch("output.json").then((data)=>{
  return data.json();
}).then((obj)=>{
   console.log(obj);
     let tableData="";
     var values=obj;
     //const str = JSON.stringify(obj, undefined, 2);
     for( i=0;i<values.length;i++){ 
       var object = JSON.parse(JSON.stringify(values[i]));
         if(object!="null"){
           tableData+=
           ` <tr>
               <td>${object["Installment Number"]}</td>
               <td>${object["Stage Number"]}</td>
               <td>${object["Installment Due Date"]}</td>
               <td>${object["Installment Amount"]}</td>
               <td>${object["Interest Rate"]}</td>
               <td>${object["Current Principal"]}</td>
               <td>${object["Current Interest"]}</td>
               <td>${object["Current Opening Balance"]}</td>
               <td>${object["Current Closing Balance"]}</td>
           </tr>`;
         }
     }
     document.getElementById("table_body").
     innerHTML=tableData;
})

