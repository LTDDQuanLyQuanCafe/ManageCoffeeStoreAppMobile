using AspNetCore_WebQuanLyQuanCafe.Models.Domain;
using AspNetCore_WebQuanLyQuanCafe.Models.Request;
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

        // GET: api/<KhachHangController>
        [HttpGet]
        [Route("all")]
        public IActionResult GetAllKhachHang()
        {
            var listTaiKhoanKH = _khachHangServices.GetAllTaiKhoanKhachHang();
            return Ok(listTaiKhoanKH.Result);
        }

        // GET api/<KhachHangController>/5
        [HttpGet("get/{email}")]
        public IActionResult GetKhachHang(string email)
        {
            var khachHang = _khachHangServices.GetTaiKhoanKhachHangInfo(email);
            return Ok(khachHang.Result);
        }

        // GET api/<KhachHangController>/check/email/phone
        [HttpGet("check/{email}/{phone}")]
        public IActionResult GetKhachHang(string email,string phone)
        {
            var khachHang = _khachHangServices.CheckInfomationExist(email,phone);
            return Ok(khachHang.Result);
        }

        // POST api/<KhachHangController>
        [HttpPost]
        [Route("create")]
        public IActionResult CreateKhachHang([FromBody] CreateRegisterRequest kh)
        {
            var khachHang = _khachHangServices.InsertTaiKhoanKhachHangInfo(kh);
            return Ok(khachHang.Result);
        }

        // GET api/<KhachHangController>/5
        [HttpGet("get-id/{dienThoai}")]
        public IActionResult getKhachHangId(string dienThoai)
        {
            var khachHang = _khachHangServices.LayMaKH(dienThoai);
            return Ok(khachHang.Result);
        }

        // POST api/<KhachHangController>
        [HttpPost]
        [Route("update")]
        public IActionResult UpdateKhachHang([FromBody] KhachHang kh)
        {
            var khachHang = _khachHangServices.UpdateInfoCustomer(kh);
            return Ok(khachHang.Result);
        }

        // POST api/<KhachHangController>
        [HttpGet("check/forgot/{ht}/{dienThoai}")]
        public IActionResult CheckForgotKhachHang(string ht, string dienThoai)
        {
            var khachHang = _khachHangServices.CheckInfoForgot(ht,dienThoai);
            return Ok(khachHang.Result);
        }

        // POST api/<KhachHangController>
        [HttpPost]
        [Route("update/pass/{maKH}/{mkMoi}")]
        public IActionResult UpdatePassword(string maKH, string mkMoi)
        {
            var khachHang = _khachHangServices.UpdatePass(maKH,mkMoi);
            return Ok(khachHang.Result);
        }
    }
}
