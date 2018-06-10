using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace WebApiProject
{
    public class RandevuController : ApiController
    {
        // GET api/<controller>
        [HttpGet]
        public IEnumerable<Randevu> Get([FromUri]Randevu randevu)
        {
            List<Randevu> r = new List<Randevu>();
            return r;
        }

        // GET api/<controller>/5
        public string Get(int id)
        {
            return "value";
        }

        // POST api/<controller>
        [HttpPost]
        public string Post([FromBody]Randevu randevu)
        {
            return "Hello from http post web api controller: " + "Tugba";
        }

        // PUT api/<controller>/5
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE api/<controller>/5
        public void Delete(int id)
        {
        }
    }
}