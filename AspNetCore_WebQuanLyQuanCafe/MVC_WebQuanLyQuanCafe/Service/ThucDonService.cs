using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using MVC_WebQuanLyQuanCafe.Models;

namespace MVC_WebQuanLyQuanCafe.Service
{
    public class ThucDonService
    {
        dbQLCFDataContext db = new dbQLCFDataContext();

        public List<THUCDON> GetThucDonFollowCate(String idCategory)
        {
            try
            {
                var thucDons = db.THUCDONs.Where(td => td.MALOAITD == idCategory).OrderBy(td=>td.DONGIA).ToList();
                for(var i = 1; i <= thucDons.Count(); i++)
                {
                    thucDons[i]._STT = i;
                }
                return thucDons;
            }
            catch (Exception ex)
            {
                return null;
            }
        }
    }
}