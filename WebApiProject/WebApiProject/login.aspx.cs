using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace WebApiProject
{
    public partial class login : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            ServicePointManager.SecurityProtocol = (SecurityProtocolType)3072;

            downloadjson();
            ServicePointManager.Expect100Continue = true;
            ServicePointManager.SecurityProtocol = SecurityProtocolType.Tls;
        }

        List<String> mailListesi = new List<String>();
        List<String> sifreListesi = new List<String>();
        public string jsonVerisi = "";
        private void downloadjson()
        {

            string url = "http://127.0.0.1:8080/musteriler/all";
            HttpWebRequest request = WebRequest.Create(url) as HttpWebRequest;
            jsonVerisi = "";
            using (HttpWebResponse response = request.GetResponse() as HttpWebResponse)
            {
                StreamReader reader = new StreamReader(response.GetResponseStream());
                //jsonVerisi adlı değişkene elde ettiği veriyi atıyoruz.
                jsonVerisi = reader.ReadToEnd();
            }
            List<Musteri> ex = Newtonsoft.Json.JsonConvert.DeserializeObject<List<Musteri>>(jsonVerisi);
            //List<Ogrenciler> ex= altinbilgisi.ogrenciler.ToList();
            //dynamic array = JsonConvert.DeserializeObject(jsonVerisi);





            for (int i = 1; i < ex.Count; i++)
            {
                mailListesi.Add(ex[i].mail);
                sifreListesi.Add(ex[i].sifre);
            }
            int count = sifreListesi.Count;
            //Label1.Text ="1 müşteri randevu tarihi:"+ ex[0].tarih.Hour.ToString();
            //Label2.Text = "2. müşteri randevu tarihi:" + ex[1].tarih.ToString();
            //Label3.Text = "3 müşteri randevu tarihi:" + ex[2].tarih.ToString();






        }

        public void btnGiris_Click(Object sender, EventArgs e)
        {
            for (int i = 0; i < mailListesi.Count; i++)
            {
                if (txtMail.Text == mailListesi[i])
                {
                    if (txtSifre.Text == sifreListesi[i])
                    {
                        Response.Redirect("home.aspx");
                    }
                }
            }
        }
    }
}