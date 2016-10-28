$.get(
    "/user",
    function(data) {
        if (data) {
            $("#loginForm").hide();
            $("#logoutForm").show();
            $("#uploadForm").show();
        }
    }
);

$.get(
    "/images",
    function(images) {
        for (var i in images) {
            var elem = $("<img>");
            elem.attr("src", "images/" + images[i].filename);
            $("#images").append(elem);
        }
    }
);