using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using MVC_WebQuanLyQuanCafe.Models;

namespace MVC_WebQuanLyQuanCafe.Service
{
    public class LoaiThucDonService
    {
        dbQLCFDataContext db = new dbQLCFDataContext();
        /// <summary>
        /// Lấy tất cả loại thực đơn
        /// </summary>
        /// <returns></returns>
        public IEnumerable<LOAITHUCDON> GetLoaiThucDons() {
            try
            {
                var loaiThucDons = new List<LOAITHUCDON>();
                loaiThucDons.Add(new LOAITHUCDON()
                {
                    MALOAITD = "ALL",
                    TENLOAITD = "TẤT CẢ THỰC ĐƠN",
                    HinhAnh = "menu.png"
                });
                loaiThucDons.AddRange(db.LOAITHUCDONs.ToList());
                return loaiThucDons;
            }
            catch
            {
                return null;
            }
        }
    }
}