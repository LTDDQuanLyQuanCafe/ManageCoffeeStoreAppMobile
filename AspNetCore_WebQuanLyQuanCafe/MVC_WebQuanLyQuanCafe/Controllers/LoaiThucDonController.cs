using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using MVC_WebQuanLyQuanCafe.Service;

namespace MVC_WebQuanLyQuanCafe.Controllers
{
    public class LoaiThucDonController : Controller
    {
        LoaiThucDonService loaiThucDonService = new LoaiThucDonService();
        public ActionResult GetAllLoaiThucDon()
        {
            return PartialView(loaiThucDonService.GetLoaiThucDons());
        }
    }
}