using AspNetCore_WebQuanLyQuanCafe.Models.Domain;
using AspNetCore_WebQuanLyQuanCafe.Services.Contracts;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace AspNetCore_WebQuanLyQuanCafe.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class KhachHangController : ControllerBase
    {

        private readonly IKhachHangServices _khachHangServices;

        public KhachHangController(IKhachHangServices khachHangServices)
        {
            _khachHangServices = khachHangServices;
        }

        // GET: api/<TaiKhoanKhachHangController>
        [HttpGet]
        [Route("all")]
        public IActionResult GetAllKhachHang()
        {
            var listTaiKhoanKH = _khachHangServices.GetAllTaiKhoanKhachHang();
            return Ok(listTaiKhoanKH.Result);
        }

        // GET api/<TaiKhoanKhachHangController>/5
        [HttpGet("get/{id}")]
        public IActionResult GetKhachHang(int id)
        {
            var khachHang = _khachHangServices.GetTaiKhoanKhachHangInfo(id);
            return Ok(khachHang.Result);
        }

        // POST api/<TaiKhoanKhachHangController>
        [HttpPost]
        public void Post([FromBody] string value)
        {
        }

        // PUT api/<TaiKhoanKhachHangController>/5
        [HttpPut("{id}")]
        public void Put(int id, [FromBody] string value)
        {
        }

        // DELETE api/<TaiKhoanKhachHangController>/5
        [HttpDelete("{id}")]
        public void Delete(int id)
        {
        }
    }
}
