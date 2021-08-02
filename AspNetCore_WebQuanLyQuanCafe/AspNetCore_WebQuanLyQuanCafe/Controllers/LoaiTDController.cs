using AspNetCore_WebQuanLyQuanCafe.Services.Contracts;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace AspNetCore_WebQuanLyQuanCafe.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class LoaiTDController : ControllerBase
    {
        private readonly ILoaiTDServices _LoaiTDServices;

        public LoaiTDController(ILoaiTDServices LoaiTDServices)
        {
            _LoaiTDServices = LoaiTDServices;
        }

        // GET: api/<TaiKhoanLoaiTDController>
        [HttpGet]
        [Route("all")]
        public IActionResult GetAllLoaiTD()
        {
            var listTaiKhoanKH = _LoaiTDServices.GetAllLoaiTD();
            return Ok(listTaiKhoanKH.Result);
        }

        //// GET api/<TaiKhoanLoaiTDController>/5
        //[HttpGet("get/{id}")]
        //public IActionResult GetLoaiTD(int id)
        //{
        //    var LoaiTD = _LoaiTDServices.GetTaiKhoanLoaiTDInfo(id);
        //    return Ok(LoaiTD.Result);
        //}

        // POST api/<TaiKhoanLoaiTDController>
        [HttpPost]
        public void Post([FromBody] string value)
        {
        }

        // PUT api/<TaiKhoanLoaiTDController>/5
        [HttpPut("{id}")]
        public void Put(int id, [FromBody] string value)
        {
        }

        // DELETE api/<TaiKhoanLoaiTDController>/5
        [HttpDelete("{id}")]
        public void Delete(int id)
        {
        }
    }
}
