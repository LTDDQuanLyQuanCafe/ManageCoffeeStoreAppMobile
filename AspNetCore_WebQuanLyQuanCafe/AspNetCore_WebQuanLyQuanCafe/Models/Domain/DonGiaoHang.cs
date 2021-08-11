using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace AspNetCore_WebQuanLyQuanCafe.Models.Domain
{
    public class DonGiaoHang
    {
        public int MaGiaoHang { get; set; }

        [Required]
        public int MaKhachHang { get; set; }

        public int? MaNhanVien { get; set; }

        public DateTime? NgayGiao { get; set; } = DateTime.Now.Date;

        [MaxLength(200)]
        public string DiaChiGiao { get; set; }

        public double? TongTien { get; set; }

        public bool TrangThai { get; set; } = false;

        [MaxLength(500)]
        public string GhiChu { get; set; }
    }
}
