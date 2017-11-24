window.onload = function() {
    $(".multi-input").on("input", function() {
        var text = this.value.trim();
        var keywords = text.split(",");

        if (keywords.length > 1) {
            // Get tagged!
            this.value = keywords[keywords.length - 1];
            for (var i = 0; i < keywords.length - 1; i++) {
                $(".my-tags").append(`<li>${keywords[i]} (that's a tag yo)</li>`);
            }
        }
    });
}