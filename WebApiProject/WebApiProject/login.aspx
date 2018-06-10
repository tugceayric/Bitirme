<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="login.aspx.cs" Inherits="WebApiProject.login" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>E-Kuaförüm Giriş Ekranı</title>
	<!-- Meta tags -->
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="keywords" content="Stunning sign up & login Form Responsive Widget, Audio and Video players, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design"
	/>
	<script>
		addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); }
	</script>
	<!-- Meta tags -->
		<!-- font-awesome icons -->
	<link rel="stylesheet" href="css/font-awesome.min.css" />
	<!-- //font-awesome icons -->
	<!--stylesheets-->
	<link href="css/styleL.css" rel='stylesheet' type='text/css' media="all">
	<!--//style sheet end here-->

<link href="//fonts.googleapis.com/css?family=Montserrat:300,400,500,600" rel="stylesheet">
</head>
<body>
   <h1 class="header-w3ls" style="color:rgba(238, 238, 238, 1)">
		EKUAFÖRÜM WEB GİRİŞ FORMU</h1>
			<div class="mid-cls">
<div class="swm-left-w3ls">
			<img src="images/k1.png" />
    <asp:Label ID="Label1" runat="server" Text="Telefon No : 05XX XXX XX XX                    "></asp:Label>
    <div><asp:Label ID="Label2" runat="server" Text="Mobil uygulamamızı hala denemediniz mi?"></asp:Label></div>
    <div><asp:Label ID="Label3" runat="server" style=" font-weight: bold; color:#f10b59"  Text="İndirmek için PlayStore'u ziyaret edin..."></asp:Label></div>
    
    
</div>

<div class="swm-right-w3ls">
 <form action="#" method="post" runat="server">
 			<div class="main">
				<div class="icon-head-wthree">
        <h2><span class="fa fa-diamond t-w3" aria-hidden="true"></span></h2>
		<h4>GİRİŞ YAP</h4>
		</div>
         <div class="form-left-w3l">
             
		
             <asp:TextBox ID="txtMail" runat="server" type="email" name="email" placeholder="Eposta adresiniz" ></asp:TextBox>
			
          <div class="clear"></div> 
		</div> 		   
		 <div class="form-right-w3ls ">
				
                <asp:TextBox ID="txtSifre" runat="server" type="password" name="password" placeholder="Şifreniz" required=""></asp:TextBox>
		         <div class="clear"></div>
		</div>
	     <div class="btnn">
             <asp:Button ID="Button1" style="background-color:#f10b59;color:white;width:280px;height:35px" runat="server" type="submit" Text="GİRİŞ YAP" onClick="btnGiris_Click" /><br />
	          <!--<button type="submit" onClick="btnGiris_Click">GİRİŞ YAP</button><br>-->
       	      <a href="#" class="for" ></a>  
       	      
        </div>
     </div>

   </form>

</div>
</div>
			<div class="copy">
		<p>&copy;2018 E-Kuaförüm Projesi İçin Tasarlanmıştır <a href="#" target="_blank"></a></p>
	</div>
</body>
</html>
