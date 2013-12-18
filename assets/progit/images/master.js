$("body").ready(function() {
    $("p").on("swipeleft", function(event) {
        var url = NavigationHelper.nextSection();
        $.mobile.changePage(url, {transition: "slide"});
    });
    $("p").on("swiperight", function(event) {
        var url = NavigationHelper.prevSection();
        $.mobile.changePage(url, {transition: "slide", reverse: true});
    });
});
