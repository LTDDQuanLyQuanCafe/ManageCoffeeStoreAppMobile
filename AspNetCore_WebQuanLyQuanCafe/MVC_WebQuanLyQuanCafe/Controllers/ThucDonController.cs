using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using MVC_WebQuanLyQuanCafe.Service;
using PagedList;

namespace MVC_WebQuanLyQuanCafe.Controllers
{
    public class ThucDonController : Controller
    {
        ThucDonService thucDonService = new ThucDonService();
        

        public ActionResult LayThucDonTheoLoai(string idCategory, int? i)
        {
            int pageSize = 6;
            int pageIndex = 1;
            pageIndex = i.HasValue ? Convert.ToInt32(i) : 1;
            ViewBag.CurrentSort = "";
            return View(thucDonService.GetThucDonFollowCate(idCategory).ToPagedList(i ?? pageIndex, pageSize));
        }
    }
}