jQuery(document).ready(function ($) {

    var Zaplanuj_jedzonko = (function () {

        var mainJS = function () {
            $('.btn-recipe-details').on('click', function () {
                $(this).parent().parent().next('.recipe_description').slideToggle();
            });
        };

        return {
            init: function () {
                mainJS();
            }
        }

    })();

    Zaplanuj_jedzonko.init();

});