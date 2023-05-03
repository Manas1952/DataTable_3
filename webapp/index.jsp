<%--<%@ taglib prefix="s" uri="/struts-tags" %>--%>

<html>
<head>
  <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.css" />

  <script crossorigin= "anonymous" src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
  <script crossorigin= "anonymous" src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.js"></script>
</head>
<center>
  <h1 style="color: green;">
    DataTable
  </h1>
</center>

<table id="tableID" class="display">
  <thead>
  <tr>
    <th>FirstName</th>
    <th>LastName</th>
    <th>Age</th>
  </tr>
  </thead>
</table>

<button id="btn">Get Data</button>

<form id="form">
  <label>First Name: </label><input id="firstname" type="text">
  <label>Last Name: </label><input id="lastname" type="text">
  <label>Age: </label><input id="age" type="text">
  <button type="submit">Submit</button>
</form>

<script>
  $(document).ready(function () {
    let data1 = [
      ["a", "b", "c"],
      ["e", "f", "g"]
    ]

    $("#btn").click(() => {
      $.ajax({
        type: "GET",
        url: "getData",
        success: (tableData) => {
          console.log('->', tableData.firstname)
          $('#tableID').dataTable({
            "data": (JSON.parse(tableData.profiles)).data
          });
        },
        error: (error) => {
          console.log('error', error)
        }
      })
    })

    $("#form").submit(() => {

      let jsonData = {
        firstname: $("#firstname").val(),
        lastname: $("#lastname").val(),
        age: $("#age").val()
      }

      $.ajax({
        type: "POST",
        url: "insertData",
        data: jsonData,
        success: (tableData) => {
          console.log('-->', tableData)
          $('#tableID').dataTable({
            "data": (JSON.parse(tableData.profiles)).data
          });
        },
        error: (error) => {
          alert(error)
        }
      })
    })

  });
</script>
</body>
</html>
