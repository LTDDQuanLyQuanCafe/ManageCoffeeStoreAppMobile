using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace AspNetCore_WebQuanLyQuanCafe.Models.Request
{
    public class CreateRegisterRequest
    {
        [MaxLength(100)]
        public string HoTen { get; set; }

        [MaxLength(10)]
        [MinLength(10)]
        public string DienThoai { get; set; }

        public string Email { get; set; }
    }
}
