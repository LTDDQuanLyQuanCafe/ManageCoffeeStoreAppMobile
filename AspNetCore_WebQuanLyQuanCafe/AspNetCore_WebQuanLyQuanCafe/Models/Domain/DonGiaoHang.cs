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


        [Required]
        public int MATHUCDON { get; set; }
        public int SOLUONGGIAO { get; set; }

        public double THANHTIEN { get; set; }

        public string? HINHANH { get; set; }

        public string TENMON { get; set; }

        public int DONGIA { get; set; }

        public string HOTEN { get; set; }

        public string DIENTHOAI { get; set; }

    }
}
