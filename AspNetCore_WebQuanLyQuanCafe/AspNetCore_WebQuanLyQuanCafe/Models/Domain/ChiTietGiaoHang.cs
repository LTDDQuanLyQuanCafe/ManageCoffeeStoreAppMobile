using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace AspNetCore_WebQuanLyQuanCafe.Models.Domain
{
    public class ChiTietGiaoHang
    {
        public int MaGiaoHang { get; set; }

        public int MaThucDon { get; set; }

        public int SLGiao { get; set; }

        public double ThanhTien { get; set; }
    }
}
