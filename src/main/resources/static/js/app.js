function searchForDentistName() {
  var searchedDentistName = document.getElementById("search-name-filter").value;
  var table = document.getElementById("dentistVisitsTable");
  var row = table.getElementsByTagName("tr");

  for (var i = 0; i < row.length; i++) {
    var nameCol = row[i].getElementsByTagName("td")[0];
    if (nameCol) {
      var dentistName = nameCol.textContent || nameCol.innerText;
      if (dentistName.toLowerCase().indexOf(searchedDentistName.toLowerCase()) > -1) {
        row[i].style.display = "";
      } else {
        row[i].style.display = "none";
      }
    }
  }
}
