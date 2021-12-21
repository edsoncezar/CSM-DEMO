<!-- //////////////////////////// ERROR CONTAINER /////////////////////////////////////////////////////  -->
<script>
	var requestObjectWarning 	= '<html:messages id="message" message="true">${message}</html:messages>';
	var requestObjectErrors 	= '<html:errors/>';
	if(requestObjectWarning.length > 0){
		message(requestObjectWarning, "MSG");
	}
	if(requestObjectErrors.length > 0){
		message(requestObjectErrors, "ERR");
	}
	
</script>
<!-- //////////////////////////// END OF ERROR CONTAINER ////////////////////////////////////////////  -->