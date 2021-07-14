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
    public class TaiKhoanKhachHangController : ControllerBase
    {
        private readonly ITaiKhoanKhachHangServices _taiKhoanKhachHangServices;

        public TaiKhoanKhachHangController(ITaiKhoanKhachHangServices taiKhoanKhachHangServices)
        {
            _taiKhoanKhachHangServices = taiKhoanKhachHangServices;
        }

        /// <summary>
        /// Dang nhap tai khoan.
        /// </summary>
        /// <param name="tenTaiKhoan">The ten tai khoan.</param>
        /// <param name="matKhau">The mat khau.</param>
        /// <returns><br/></returns>
        [HttpGet("login")]
        public IActionResult DangNhapTaiKhoan(string tenTaiKhoan, string matKhau)
        {
            var checkLogin = _taiKhoanKhachHangServices.KiemTraDangNhap(tenTaiKhoan, matKhau).Result;
            return Ok(checkLogin);
        }

    }
}
