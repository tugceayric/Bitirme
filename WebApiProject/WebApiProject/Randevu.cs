using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApiProject
{
    public class Randevu
    {
        public int id { get; set; }
        public DateTime tarih { get; set; }
        public Musteri musteri { get; set; }
        public IList<IslemListe> islemListe { get; set; }
    }
}