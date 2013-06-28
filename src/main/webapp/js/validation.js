function validate() {
    var size = document.getElementsByName("appropriatePage").length;
    var page, perPage, maxPage;
    for (i = 0; i < size; i++) {
        page = document.getElementsByName("appropriatePage")[i].value;
        perPage = document.getElementsByName("itemsPerPage")[i].value;
        if (isNaN(perPage)) {
            document.getElementsByClassName("pageError")[i].innerHTML = "Not a number";
            return false;
        }
        if (isNaN(page)) {
            document.getElementsByClassName("pageError")[i].innerHTML = "Not a number";
            return false;
        }
        if (perPage <= 0) {
            document.getElementsByClassName("pageError")[i].innerHTML = "Page number must be positive";
            return false;
        }
        if (page <= 0) {
            document.getElementsByClassName("pageError")[i].innerHTML = "Page number must be positive";
            return false;
        }
        maxPage = (employeeCount - 1) / perPage + 1;
        maxPage = parseInt(maxPage, 10);
        if (maxPage < page) {
            document.getElementsByClassName("pageError")[i].innerHTML = "Page number must be less or equal than " + maxPage;
            return false;
        }
        document.getElementsByClassName("pageError")[i].innerHTML = "";
    }
    return true;
}