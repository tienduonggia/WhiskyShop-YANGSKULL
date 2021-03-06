<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/views/layouts/admin/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<head>
<title>Rượu nhập khẩu - YANGSKULL</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<meta name="keywords"
	content="Watches Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />

<script type="application/x-javascript">
	
	
	
		
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 




</script>

<script src="https://cdn.jsdelivr.net/npm/autonumeric@4.5.4"></script>

<!-- 		<c:url value="/assets/admin/ "/>		 -->

<link rel="stylesheet"
	href="<c:url value="/assets/admin/css/bootstrap.min.css"/>">
<!-- //bootstrap-css -->
<!-- Custom CSS -->
<link href="<c:url value="/assets/admin/css/style.css"/>"
	rel='stylesheet' type='text/css' />
	
	<link href="<c:url value="/assets/admin/css/customCSS.css"/>"
	rel='stylesheet' type='text/css' />
<link href="<c:url value="/assets/admin/css/style-responsive.css "/>" rel="stylesheet" />
<!-- font CSS -->
<link
	href='//fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300italic,400italic,500,500italic,700,700italic,900,900italic'
	rel='stylesheet' type='text/css'>
<!-- font-awesome icons -->
<link rel="stylesheet" href="<c:url value="/assets/admin/css/font.css "/>" type="text/css" />
<link href="<c:url value="/assets/admin/css/font-awesome.css "/>" rel="stylesheet">
<link rel="stylesheet" href="<c:url value="/assets/admin/css/morris.css "/>" type="text/css" />
<!-- calendar -->
<link rel="stylesheet" href="<c:url value="/assets/admin/css/monthly.css "/>">
<!-- //calendar -->
<!-- //font-awesome icons -->
<script src="<c:url value="/assets/admin/js/jquery2.0.3.min.js "/>"></script>
<script src="<c:url value="/assets/admin/js/raphael-min.js "/>"></script>
<script src="<c:url value="/assets/admin/js/morris.js "/>"></script>


<script src="<c:url value="/ckeditor/ckeditor.js "/>"></script>
<script src="<c:url value="/ckfinder/ckfinder.js "/>"></script>







<link rel="shortcut icon"
	href="<c:url value="/assets/user/images/favicon.ico" />">




<style>

.my-custom-scrollbar {
position: relative;
height: 477px;
overflow: auto;
}
.table-wrapper-scroll-y {
display: block;
}
</style>
<decorator:head />
</head>

<body>
<section id="container">
<!--  Header-->
	<%@include file="/WEB-INF/views/layouts/admin/header.jsp"%>



<!--  body-->
<section id="main-content">
<section class="wrapper">
	<decorator:body />
</section>


<!--  footer-->
	<%@include file="/WEB-INF/views/layouts/admin/footer.jsp"%>
</section>
</section>




<!-- 		<c:url value="/assets/admin/ "/>		 -->
<script src="<c:url value="/assets/admin/js/bootstrap.js "/> "></script>
<script src="<c:url value="/assets/admin/js/jquery.dcjqaccordion.2.7.js "/> "></script>
<script src="<c:url value="/assets/admin/js/scripts.js "/> "></script>
<script src="<c:url value="/assets/admin/js/jquery.slimscroll.js "/> "></script>
<script src="<c:url value="/assets/admin/js/jquery.nicescroll.js "/> "></script>
<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="js/flot-chart/excanvas.min.js"></script><![endif]-->
<script src="<c:url value="/assets/admin/js/jquery.scrollTo.js "/>"></script>
<!-- morris JavaScript -->	
<script>
	$(document).ready(function() {
		//BOX BUTTON SHOW AND CLOSE
	   jQuery('.small-graph-box').hover(function() {
		  jQuery(this).find('.box-button').fadeIn('fast');
	   }, function() {
		  jQuery(this).find('.box-button').fadeOut('fast');
	   });
	   jQuery('.small-graph-box .box-close').click(function() {
		  jQuery(this).closest('.small-graph-box').fadeOut(200);
		  return false;
	   });
	   
	    //CHARTS
	    function gd(year, day, month) {
			return new Date(year, month - 1, day).getTime();
		}
		
		graphArea2 = Morris.Area({
			element: 'hero-area',
			padding: 10,
        behaveLikeLine: true,
        gridEnabled: false,
        gridLineColor: '#dddddd',
        axes: true,
        resize: true,
        smooth:true,
        pointSize: 0,
        lineWidth: 0,
        fillOpacity:0.85,
			data: [
				{period: '2015 Q1', iphone: 2668, ipad: null, itouch: 2649},
				{period: '2015 Q2', iphone: 15780, ipad: 13799, itouch: 12051},
				{period: '2015 Q3', iphone: 12920, ipad: 10975, itouch: 9910},
				{period: '2015 Q4', iphone: 8770, ipad: 6600, itouch: 6695},
				{period: '2016 Q1', iphone: 10820, ipad: 10924, itouch: 12300},
				{period: '2016 Q2', iphone: 9680, ipad: 9010, itouch: 7891},
				{period: '2016 Q3', iphone: 4830, ipad: 3805, itouch: 1598},
				{period: '2016 Q4', iphone: 15083, ipad: 8977, itouch: 5185},
				{period: '2017 Q1', iphone: 10697, ipad: 4470, itouch: 2038},
			
			],
			lineColors:['#eb6f6f','#926383','#eb6f6f'],
			xkey: 'period',
            redraw: true,
            ykeys: ['iphone', 'ipad', 'itouch'],
            labels: ['All Visitors', 'Returning Visitors', 'Unique Visitors'],
			pointSize: 2,
			hideHover: 'auto',
			resize: true
		});
		
	   
	});
	</script>
<!-- calendar -->
	<script type="text/javascript" src="<c:url value="/assets/admin/js/monthly.js "/>"></script>
	<script type="text/javascript">
		$(window).load( function() {

			$('#mycalendar').monthly({
				mode: 'event',
				
			});

			$('#mycalendar2').monthly({
				mode: 'picker',
				target: '#mytarget',
				setWidth: '250px',
				startHidden: true,
				showTrigger: '#mytarget',
				stylePast: true,
				disablePast: true
			});

		switch(window.location.protocol) {
		case 'http:':
		case 'https:':
		// running on a server, should be good.
		break;
		case 'file:':
		alert('Just a heads-up, events will not work when run locally.');
		}

		});
	</script>


</body>
</html>