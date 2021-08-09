using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace AspNetCore_WebQuanLyQuanCafe.Models.Domain
{
    public class TinTuc
    {
        [Required]
        public int MaTinTuc { get; set; }

        [MaxLength(100)]
        public string HinhAnh { get; set; }

        [MaxLength(300)]
        public string TieuDe { get; set; }

        [MaxLength(500)]
        public string NoiDung { get; set; }
    }
}
