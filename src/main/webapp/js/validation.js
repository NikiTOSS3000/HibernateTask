function validate() {
    var page = document.getElementById("appropriatePage");
    var perPage = document.getElementById("employeePerPage");
    if (page.value <= 0) {
        document.getElementById("pageError").innerHTML = "Page number must be positive";
        return false;
    }
    var maxPage = (employeeCount - 1) / perPage.value + 1;
    maxPage = parseInt(maxPage, 10);
    if (maxPage < page.value) {
        document.getElementById("pageError").innerHTML = "Page number must be less or equal than " + maxPage;
        return false;
    }
    return true;
}