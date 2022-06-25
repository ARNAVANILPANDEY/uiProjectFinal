fetch('SimpleCalculator.json').then((data)=>{
     return data.json();
   }).then((obj)=>{
      console.log(obj);
        let tableData="";
        var values=obj["Simple Schedule"];
        for( i=0;i<9;i++){ 
          var object = JSON.parse(JSON.stringify(values[i]));
            if(object!="null"){
              tableData+=
              ` <tr>
                  <td>${object.Column1}</td>
                  <td>${object.Column2}</td>
                  <td>${object.Column3}</td>
                  <td>${object.Column4}</td>
                  <td>${object.Column5}</td>
                  <td>${object.Column6}</td>
                  <td>${object.Column7}</td>
                  <td>${object.Column8}</td>
                  <td>${object.Column9}</td>
                  <td>${object.Column10}</td>
                  <td>${object.Column11}</td>
                  <td>${object.Column12}</td>
                  <td>${object.Column13}</td>
                  <td>${object.Column14}</td>
                  <td>${object.Column15}</td>
              </tr>`;
            }
        }
        document.getElementById("table_body").
        innerHTML=tableData;
   })

