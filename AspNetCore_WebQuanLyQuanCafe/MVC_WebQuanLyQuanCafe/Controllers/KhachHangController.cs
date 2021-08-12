using MVC_WebQuanLyQuanCafe.Models;
using PagedList;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace MVC_WebQuanLyQuanCafe.Controllers
{
    public class KhachHangController : Controller
    {
        dbQLCFDataContext db = new dbQLCFDataContext();
        // GET: KhachHang
        public ActionResult GetKhacHang(int? i)
        {
            int pageSize = 4;
            int pageIndex = 1;
            pageIndex = i.HasValue ? Convert.ToInt32(i) : 1;
            ViewBag.CurrentSort = "";
            List<KHACHHANG> khachHangs = db.KHACHHANGs.OrderByDescending(td => td.MAKHACHHANG).ToList();
            khachHangs.Where(kh=>kh.HINHANH == null).ToList().ForEach(kh => kh.HINHANH = "man.png");
            return View(khachHangs.ToPagedList(i ?? pageIndex, pageSize));
        }
    }
}