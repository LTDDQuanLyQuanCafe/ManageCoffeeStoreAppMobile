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
        public string MaKhachHang { get; set; }

        /// <summary>
        /// Gets or sets the ten khach hang.
        /// </summary>
        /// <value>
        /// The ten khach hang.
        /// </value>
        public string TenKhachHang { get; set; }

        /// <summary>
        /// Gets or sets the ngay sinh.
        /// </summary>
        /// <value>
        /// The ngay sinh.
        /// </value>
        public DateTime? NgaySinh { get; set; }

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
        [EmailAddress]
        public string Email { get; set; }

        /// <summary>
        /// Gets or sets the dien thoai.
        /// </summary>
        /// <value>
        /// The dien thoai.
        /// </value>
        [Phone]
        [MaxLength(10), MinLength(10)]
        public string DienThoai { get; set; }

        /// <summary>
        /// Gets or sets the dia chi.
        /// </summary>
        /// <value>
        /// The dia chi.
        /// </value>
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

        /// <summary>
        /// Gets or sets the mat khau.
        /// </summary>
        /// <value>
        /// The mat khau.
        /// </value>
        [MaxLength(30), MinLength(6)]
        public string MatKhau { get; set; }

        /// <summary>
        /// Gets or sets the ngay tao.
        /// </summary>
        /// <value>
        /// The ngay tao.
        /// </value>
        public DateTime? NgayTao { get; set; }
    }
}
