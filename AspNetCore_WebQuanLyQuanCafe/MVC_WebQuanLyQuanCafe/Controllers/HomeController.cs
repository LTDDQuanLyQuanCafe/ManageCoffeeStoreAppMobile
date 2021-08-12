using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using MVC_WebQuanLyQuanCafe.Models;

namespace MVC_WebQuanLyQuanCafe.Controllers
{
    public class HomeController : Controller
    {
        dbQLCFDataContext db = new dbQLCFDataContext();
        // GET: Home
        public ActionResult Index()
        {
            if (Session["TaiKhoanAdmin"] != null)
                return View();
            return RedirectToAction("DangNhap","NhanVien");
        }
    }
}