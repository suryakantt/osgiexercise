$(window).adaptTo("foundation-registry").register("foundation.validation.validator", {
    selector: "[data-should-contain]",
    validate: function(el) {
		var shouldContain = el.getAttribute("data-should-contain");

        var input = el.value;

        if(value.indexOf(shouldContain) === -1){
			return "The field should contain " + shouldContain + ". Current value is " + el.value;
        }

    }

});