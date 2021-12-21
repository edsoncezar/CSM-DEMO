<link rel="stylesheet" href="Chart/Contents/Style.css" type="text/css" />
<script language="JavaScript" src="Chart/JSClass/FusionCharts.js"></script>
<table width="100%" border="0" cellspacing="0" cellpadding="3" align="center">
  <tr> 
    <td valign="top" class="text" align="center"> <div id="chartdiv" align="center"> 
        FusionCharts. </div>
      <script type="text/javascript">
		   var chart = new FusionCharts("Chart/Charts/Line.swf", "ChartId", "250", "200", "0", "0");
		   chart.setDataURL("Chart/Data/Line2D.xml");		   
		   chart.render("chartdiv");
		</script> </td>
  </tr>
</table>
