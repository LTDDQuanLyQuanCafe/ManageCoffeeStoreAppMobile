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
                return db.LOAITHUCDONs.ToList();
            }
            catch
            {
                return null;
            }
        }
    }
}