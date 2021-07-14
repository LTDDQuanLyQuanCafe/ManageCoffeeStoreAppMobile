using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace AspNetCore_WebQuanLyQuanCafe.Models.Domain
{
    public class KhachHang
    {
        /// <summary>
        /// Gets or sets the ma khach hang.
        /// </summary>
        /// <value>
        /// The ma khach hang.
        /// </value>
        [Required]
        public string MaKhachHang { get; set; }

        /// <summary>
        /// Gets or sets the ten khach hang.
        /// </summary>
        /// <value>
        /// The ten khach hang.
        /// </value>
        [Required]
        public string TenKhachHang { get; set; }

        /// <summary>
        /// Gets or sets the ngay sinh.
        /// </summary>
        /// <value>
        /// The ngay sinh.
        /// </value>
        [Required]
        public DateTime NgaySinh { get; set; }

        /// <summary>
        /// Gets or sets the gioi tinh.
        /// </summary>
        /// <value>
        /// The gioi tinh.
        /// </value>
        public string GioiTinh { get; set; } = "Nam";

        /// <summary>
        /// Gets or sets the email.
        /// </summary>
        /// <value>
        /// The email.
        /// </value>
        [Required]
        [EmailAddress]
        public string Email { get; set; }

        /// <summary>
        /// Gets or sets the dien thoai.
        /// </summary>
        /// <value>
        /// The dien thoai.
        /// </value>
        [Required]
        [Phone]
        [MaxLength(10), MinLength(10)]
        public string DienThoai { get; set; }

        /// <summary>
        /// Gets or sets the ma loai kh.
        /// </summary>
        /// <value>
        /// The ma loai kh.
        /// </value>
        [Required]
        public string MaLoaiKH { get; set; }

        /// <summary>
        /// Gets or sets the dia chi.
        /// </summary>
        /// <value>
        /// The dia chi.
        /// </value>
        [Required]
        [MaxLength(200)]
        public string DiaChi { get; set; }

        /// <summary>
        /// Gets or sets the diem tich luy.
        /// </summary>
        /// <value>
        /// The diem tich luy.
        /// </value>
        public float DiemTichLuy { get; set; } = 0;

        /// <summary>
        /// Gets or sets the hinh anh.
        /// </summary>
        /// <value>
        /// The hinh anh.
        /// </value>
        public string HinhAnh { get; set; }
    }
}
