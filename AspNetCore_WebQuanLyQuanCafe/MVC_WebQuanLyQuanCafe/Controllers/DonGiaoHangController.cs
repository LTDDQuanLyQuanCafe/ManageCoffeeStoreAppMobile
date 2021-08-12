using MVC_WebQuanLyQuanCafe.Models;
using PagedList;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace MVC_WebQuanLyQuanCafe.Controllers
{
    public class DonGiaoHangController : Controller
    {
        dbQLCFDataContext db = new dbQLCFDataContext();
        public ActionResult GetDsDGH(string search, int? i)
        {
            int pageSize = 5;
            int pageIndex = 1;
            pageIndex = i.HasValue ? Convert.ToInt32(i) : 1;
            ViewBag.CurrentSort = search;
            return View(db.DONGIAOHANGs.Where(x => x.KHACHHANG.HOTEN.Contains(search) 
            || x.NHANVIEN.TENNHANVIEN.Contains(search)|| x.DIACHIGIAO.Contains(search)
            || search == null).ToList().ToPagedList(i ?? pageIndex, pageSize));
        }
    }
}