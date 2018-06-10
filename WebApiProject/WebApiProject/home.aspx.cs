using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Net;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.Script.Serialization;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace WebApiProject
{
    public partial class home : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            ServicePointManager.SecurityProtocol = (SecurityProtocolType)3072;
           
            downloadjson();
            ServicePointManager.Expect100Continue = true;
            ServicePointManager.SecurityProtocol = SecurityProtocolType.Tls;
            //dSaat.SelectedIndex = 0;

        }

        List<DateTime> tarihListesi = new List<DateTime>();
        List<String> saatListesi = new List<String>();
        public string jsonVerisi = "";
        private void downloadjson()
        {

            /*   var w = new WebClient();

            string url = "http://www.doviz.com/api/v1/golds/all/latest";
            var jsondata = w.DownloadString(url);
            var result = JsonConvert.DeserializeObject<altin>(jsondata);*/

            string adres = "http://www.eniskurtayyilmaz.com/ornek.json";
            WebRequest istek = HttpWebRequest.Create(adres);
            WebResponse cevap;//www.bilisimogretmeni.com
            cevap = istek.GetResponse();
            StreamReader donenBilgiler = new StreamReader(cevap.GetResponseStream());
            string bilgilerial = donenBilgiler.ReadToEnd();
            //List<Ogrenciler> altinbilgisi = Newtonsoft.Json.JsonConvert.DeserializeObject<List<Ogrenciler>>(bilgilerial);

            //string url = "<a href="http://www.eniskurtayyilmaz.com/ornek.json" target="_blank">www.xyzuniversitesi.com/ornek.json</a>";

            //string url = "http://www.eniskurtayyilmaz.com/ornek.json";
            string url = "http://127.0.0.1:8080/randevular/all";
            HttpWebRequest request = WebRequest.Create(url) as HttpWebRequest;
            jsonVerisi = "";
            using (HttpWebResponse response = request.GetResponse() as HttpWebResponse)
            {
                StreamReader reader = new StreamReader(response.GetResponseStream());
                //jsonVerisi adlı değişkene elde ettiği veriyi atıyoruz.
                jsonVerisi = reader.ReadToEnd();
            }
            List<Randevu> ex = Newtonsoft.Json.JsonConvert.DeserializeObject<List<Randevu>>(jsonVerisi);
            //List<Ogrenciler> ex= altinbilgisi.ogrenciler.ToList();
            //dynamic array = JsonConvert.DeserializeObject(jsonVerisi);
          
         



            for (int i=1;i<ex.Count;i++)
            {
                tarihListesi.Add(ex[i].tarih.Date);
                saatListesi.Add(ex[i].tarih.Hour.ToString());
            }
            int count=saatListesi.Count;
            //Label1.Text ="1 müşteri randevu tarihi:"+ ex[0].tarih.Hour.ToString();
            //Label2.Text = "2. müşteri randevu tarihi:" + ex[1].tarih.ToString();
            //Label3.Text = "3 müşteri randevu tarihi:" + ex[2].tarih.ToString();

          
            

            

        }

        public void btnGonder_Click(Object sender,EventArgs e)
        {
            Random r = new Random();
            int random = r.Next(0, 1212);

            Random r1 = new Random();
            int rTel = r1.Next(123546, 999874);

            Musteri m = new Musteri();
            m.id = 10000 + random;
            m.ad = txtAdSoyad.Text;
            m.telefon = 05000000 + rTel;
            //m.telefon = Convert.ToInt32(txtTelefon.Text);
            Randevu yeniKisi = new Randevu();
            yeniKisi.id = 11000 + random;
            string tarih = calendar.SelectedDate.ToShortDateString() + " " + dSaat.SelectedValue + ":00:00";
            yeniKisi.tarih=Convert.ToDateTime(tarih) ;           
            yeniKisi.musteri = m;

            List<Randevu> personsToAdd = new List<Randevu>();
            personsToAdd.Add(yeniKisi);

            for (int i = 0; i < tarihListesi.Count; i++)
            {
                if (calendar.SelectedDate == tarihListesi[i])
                {
                    if (dSaat.SelectedItem.Value == saatListesi[i])
                    {
                        string message = "Seçmek istediğiniz saat dolu! Lütfen başka bir saat seçin";
                        System.Text.StringBuilder sb = new System.Text.StringBuilder();
                        sb.Append("<script type = 'text/javascript'>");
                        sb.Append("window.onload=function(){");
                        sb.Append("alert('");
                        sb.Append(message);
                        sb.Append("')};");
                        sb.Append("</script>");
                        ClientScript.RegisterClientScriptBlock(this.GetType(), "alert", sb.ToString());
                        //Response.Write("<script LANGUAGE='JavaScript' >alert('BAŞKA SAAT SEÇİN')</script>");
                    }

                    else
                    {

                    }
                }
                else
                {/*
                    try {


                       /* string message = "Randevu kaydınız başarıyla alınmıştır.Bizi tercih ettiğiniz çin teşekkür ederiz...";
                        System.Text.StringBuilder sb = new System.Text.StringBuilder();
                        sb.Append("<script type = 'text/javascript'>");
                        sb.Append("window.onload=function(){");
                        sb.Append("alert('");
                        sb.Append(message);
                        sb.Append("')};");
                        sb.Append("</script>");
                        ClientScript.RegisterClientScriptBlock(this.GetType(), "alert", sb.ToString());*/

                    /*    List<Randevu> list = JsonConvert.DeserializeObject<List<Randevu>>(jsonVerisi);
                        list.Add(personsToAdd[0]);
                        var jsonToOutput = JsonConvert.SerializeObject(list.ToArray(), Formatting.Indented);
                       // BuildDateTimeFromYAFormat(personsToAdd[0].tarih.ToString());
                        
                        string url = "http://127.0.0.1:8080/randevular/add/" + "20-01-2019-06:00:00.000+0000" + "/" + personsToAdd[0].id + "/" + "7" + "/2";




                        var httpWebRequest = (HttpWebRequest)WebRequest.Create(url);
                        httpWebRequest.ContentType = "application/json";
                        
                        httpWebRequest.Method = "GET";
                        ServicePointManager.SecurityProtocol = SecurityProtocolType.Tls12 | SecurityProtocolType.Tls11 | SecurityProtocolType.Tls;

                        using (var streamWriter = new StreamWriter(httpWebRequest.GetRequestStream()))
                        {
                            string json = new JavaScriptSerializer().Serialize(personsToAdd[0]);
                            streamWriter.Write(json);
                            streamWriter.Flush();
                            streamWriter.Close();
                        }

                        var httpResponse = (HttpWebResponse)httpWebRequest.GetResponse();
                        using (var streamReader = new StreamReader(httpResponse.GetResponseStream()))
                        {
                            var result = streamReader.ReadToEnd();
                        }


                    }
                    catch (Exception ex)
                    {
                        Console.WriteLine(ex);
                    }
                    */
                    
                }
            }

        }


        private DateTime BuildDateTimeFromYAFormat(string dateString)
        {
            Regex r = new Regex(@"^\d{4}\d{2}\d{2}T\d{2}\d{2}Z$");
            if (!r.IsMatch(dateString))
            {
                throw new FormatException(
                    string.Format("{0} is not the correct format. Should be yyyyMMddThhmmZ", dateString));
            }

            DateTime dt = DateTime.ParseExact(dateString, "yyyy-MM-ddThh:mmZ", CultureInfo.InvariantCulture, DateTimeStyles.AssumeUniversal);

            return dt;
        }



    }
}