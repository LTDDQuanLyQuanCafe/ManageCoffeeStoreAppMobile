using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace AspNetCore_WebQuanLyQuanCafe.Models.Request
{
    public class CreateKhachHangRequest
    {
        public string HoTen { get; set; }
        public string DienThoai { get; set; }
        public string Email { get; set; }
    }
}
