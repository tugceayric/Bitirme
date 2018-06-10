<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="home.aspx.cs" Inherits="WebApiProject.home" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>eKuaförüm Randevu</title>
<!-- Meta tag Keywords -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Cab Booking Form Responsive Widget,Login form widgets, Sign up Web forms , Login signup Responsive web form,Flat Pricing table,Flat Drop downs,Registration Forms,News letter Forms,Elements" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- Meta tag Keywords -->

<!-- css files -->
<link href="css/style.css" rel="stylesheet" type="text/css" media="all">
<link href="css/wickedpicker.css" rel="stylesheet" type='text/css' media="all" />
<link rel="stylesheet" href="css/jquery-ui.css" />
<!-- //css files -->

<!-- online-fonts -->
<link href="//fonts.googleapis.com/css?family=Amaranth:400,400i,700,700i" rel="stylesheet">
<!--//online-fonts -->

  

</head>
<body>
    <div class="header">
	<h1>e-Kuaförüm Randevu Ekranı</h1>
</div>

<div class="w3-main">
	<!-- Main -->
	<div class="about-bottom">
		<div class="w3l_about_bottom_right two">
			<h2 class="tittle"><img src="images/k1.png" width="45px" alt=""><span>Hemen Rezervasyon Yapın</span></h2>
			<div class="book-form">

			    <form action="#" method="post" runat="server">
					<div class="form-date-w3-agileits">
						<div class="form-agileits">
							<label> Adınız ve Soyadınız :</label>
						</div>
						<div class="form-agileits-2">
							
                            <asp:TextBox ID="txtAdSoyad" runat="server" type="text" name="name" ></asp:TextBox>
						</div>
						<div class="clear"> </div>
					</div>
					<div class="form-date-w3-agileits second-agile">
						<div class="form-agileits">
							<label> Telefon Numaranız :</label>
						</div>
						<div class="form-agileits-2">
							
                            <asp:TextBox ID="txtTelefon" runat="server" type="text" name="name" placeholder="" required=""></asp:TextBox>
						</div>
						<div class="clear"> </div>
					</div>
					
					
					<div class="form-date-w3-agileits">
						<div class="form-agileits">
							<label>Randevu Tarihi :</label>
						</div>
						<div class="form-agileits-2">
							<!--<input  id="datepicker" name="date" type="text" value="Lütfen geçerli bir tarih seçin" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Lütfen geçerli bir tarih seçin';}" required="">-->
                            <asp:Calendar ID="calendar" runat="server"  style="margin-left:6px;" name="date" type="text" value="Lütfen geçerli bir tarih seçin" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Lütfen geçerli bir tarih seçin';}" required=""></asp:Calendar>
						</div>	
						<div class="clear"> </div>						
					</div>
					<div class="form-date-w3-agileits">
						<div class="form-agileits">
							<label>Randevu Saati :</label>
						</div>
						<div class="form-agileits-2">
                            <asp:DropDownList ID="dSaat" runat="server" type="text" style="margin-top:9px;background-color:#282828; width:400px; color:lightgray" name="Time" class="l" value="Lütfen geçerli bir saat seçin">
                                <asp:ListItem Value="9">09:00</asp:ListItem>
                                <asp:ListItem Value="10">10:00</asp:ListItem>
                                <asp:ListItem Value="13">13:00</asp:ListItem>
                                <asp:ListItem Value="19">19:00</asp:ListItem>
                            </asp:DropDownList>
						</div>
                        
						<div class="clear"> </div>
					</div>
					<div class="make">
						  
                        <asp:Button ID="btnGonder" runat="server" Text="Randevuyu Onayla" type="submit" value="Randevuyu Onayla" onClick="btnGonder_Click" />
					</div>
				</form>
			</div>
		</div>
		<div class="clear"> </div>
	</div>
</div>
<!-- //Main -->


	<!-- js-scripts-->
		<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
		
		<!-- Time -->
		<script type="text/javascript" src="js/wickedpicker.js"></script>
			<script type="text/javascript">
				$('.timepicker').wickedpicker({twentyFour: false});
			</script>
		<!-- //Time -->
		
			<!-- Calendar -->
				<script src="js/jquery-ui.js"></script>
				  <script>
						  $(function() {
							$( "#datepicker,#datepicker1,#datepicker2,#datepicker3" ).datepicker();
						  });
				  </script>
			<!-- //Calendar -->

  
	<!-- //js-scripts-->
</body>

</html>
