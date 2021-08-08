using MVC_WebQuanLyQuanCafe.Models;
using MVC_WebQuanLyQuanCafe.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace MVC_WebQuanLyQuanCafe.Controllers
{
    public class NhanVienController : Controller
    {
        NhanVienService nhanVienService = new NhanVienService();
        /// <summary>
        /// Đăng nhập tài khoản nhân viên
        /// </summary>
        /// <returns></returns>
        [HttpPost()]
        [ValidateAntiForgeryToken]
        public ActionResult DangNhap(string TenTaiKhoan, string MatKhau)
        {
            if (String.IsNullOrEmpty(TenTaiKhoan))
            {
                ViewBag.LoiTen = "Tên tài khoản admin không được để trống!";
            }
            if (String.IsNullOrEmpty(MatKhau))
            {
                ViewBag.LoiMK = "Mật khẩu admin không được để trống!";
                return View();
            }
            if (ModelState.IsValid)
            {
                NHANVIEN nv = nhanVienService.KiemTraDangNhap(TenTaiKhoan, MatKhau);
                if (nv == null)
                {
                    ViewBag.LoiTK = "Tài khoản admin chưa tồn tại!";
                }
                else
                {
                    Session["TaiKhoanAdmin"] = TenTaiKhoan;
                    Session["TenAdmin"] = nv.TENNHANVIEN;
                    Session["HinhAdmin"] = nv.HINHANH;

                    return View("~/Views/Home/Index.cshtml");
                }
            }
            return View();
        }
        [Route("[{controller}/{action}]")]
        public ActionResult DangNhap()
        {
            return View();
        }

    }
}