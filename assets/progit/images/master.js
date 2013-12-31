$("body").ready(function() {
    console.warn("what is the shit");
    $("p").on("swipeleft", function(event) {
        var url = NavigationHelper.nextSection();
        console.warn("jump to next section url " + url);
        $.mobile.changePage(url, {transition: "slide"});
    });
    $("p").on("swiperight", function(event) {
        var url = NavigationHelper.prevSection();
        console.warn("go back to the previous section" + url);
        $.mobile.changePage(url, {transition: "slide", reverse: true});
    });
    $("a#back").on("click", function(event) {
        window.history.back();
    });
});
