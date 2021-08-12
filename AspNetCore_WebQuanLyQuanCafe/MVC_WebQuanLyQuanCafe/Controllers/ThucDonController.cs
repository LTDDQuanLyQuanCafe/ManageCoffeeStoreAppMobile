using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using MVC_WebQuanLyQuanCafe.Models;
using MVC_WebQuanLyQuanCafe.Service;
using PagedList;

namespace MVC_WebQuanLyQuanCafe.Controllers
{
    public class ThucDonController : Controller
    {
        dbQLCFDataContext db = new dbQLCFDataContext();
        ThucDonService thucDonService = new ThucDonService();
        // GET: ThucDon
        [HttpGet]
        [Route("[{controller}/{action}/{idCategory}]")]
        public ActionResult LayThucDonTheoLoai(string idCategory, int? i)
        {
            int pageSize = 4;
            int pageIndex = 1;
            pageIndex = i.HasValue ? Convert.ToInt32(i) : 1;
            ViewBag.CurrentSort = "";
            List<THUCDON> thucDons = new List<THUCDON>();
            try
            {
                if (idCategory == null)
                {
                    thucDons = db.THUCDONs.Where(td => td.MALOAITD.Equals(Session["MaLoai"].ToString())).OrderByDescending(td => td.MATHUCDON).ToList();
                }
                else
                {
                    thucDons = db.THUCDONs.Where(td => td.MALOAITD.Equals(idCategory)).OrderByDescending(td => td.MATHUCDON).ToList();
                    Session["MaLoai"] = thucDons.First().MALOAITD;

                    for (int ii = 0; ii < thucDons.Count(); ii++)
                    {
                        thucDons[ii]._STT = ii + 1;
                    }
                }
            }
            catch (Exception ex)
            {
                return null;
            }
            return View(thucDons.ToPagedList(i ?? pageIndex, pageSize));
        }

        public ActionResult CreateThucDon()
        {
            ViewBag.MALOAITD = new SelectList(db.LOAITHUCDONs, "MALOAITD", "TENLOAITD");
            return PartialView();
        }

        [HttpPost]
        public ActionResult CreateThucDon(THUCDON thucDon, FormCollection f)
        {
            try
            {
                ViewBag.MALOAITD = new SelectList(db.LOAITHUCDONs, "MALOAITD", "TENLOAITD");
                thucDon = new THUCDON() {
                    TENMON = f["TenMon"],
                    HINHANH = f["HinhAnh"],
                    DONGIA = int.Parse(f["donGia"]),
                    DVT = f["dvt"],
                    MALOAITD = f["MaLoaiTD"],
                    MOTA = f["moTa"],
                };

                bool flag = true;
                if (thucDon.TENMON == null)
                {
                    ViewBag.LoiTen = "Tên món không được để trống!";
                    flag = false;
                }

                if (thucDon.DONGIA == null)
                {
                    ViewBag.LoiDG = "Đơn giá món không được để trống!";
                    flag = false;
                }

                if (thucDon.DVT == null)
                {
                    ViewBag.LoiDVT = "Đơn vị tính món không được để trống!";
                    flag = false;
                }
                if (thucDon.MALOAITD == null)
                {
                    ViewBag.LoiDept = "Loại thực đơn không được để trống! ";
                    flag = false;
                }

                if (thucDon.HINHANH != null)
                {
                    string[] kt = thucDon.HINHANH.Split('.');
                    for (int i = 0; i < kt.Length; i++)
                    {
                        if (!kt[kt.Length - 1].Equals("jpg") && !kt[kt.Length - 1].Equals("png"))
                        {
                            flag = false;
                            ViewBag.LoiImage = " Image không phù hợp!";
                        }
                    }
                }
                else
                {
                    ViewBag.LoiImage = "Hình ảnh món không được để trống!";
                    flag = false;
                }

                if (flag == true)
                {
                    db.THUCDONs.InsertOnSubmit(thucDon);
                    db.SubmitChanges();
                    return RedirectToAction("LayThucDonTheoLoai", "ThucDon", thucDon.MALOAITD);
                }

                return null;
            }
            catch (Exception ex)
            {
                return PartialView(thucDon);
            }
        }

        #region private function
        private List<THUCDON> processAll(List<THUCDON> thucDons)
        {
            thucDons = db.THUCDONs.OrderBy(td => td.DONGIA).ToList();
            Session["MaLoai"] = "ALL";
            return thucDons;
        }
        #endregion
    }
}