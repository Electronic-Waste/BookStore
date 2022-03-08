function showBook(bookClassName)
{
    let bookClass = document.getElementsByClassName(bookClassName)[0];
    let imgClass = bookClass.getElementsByTagName("img");
    let len = imgClass.length;

    for (let i = 0; i < len; ++i) {
        if (imgClass[i].style.visibility === "hidden") {
            imgClass[i].style.visibility = "visible";
            break;
        }
    }

}
