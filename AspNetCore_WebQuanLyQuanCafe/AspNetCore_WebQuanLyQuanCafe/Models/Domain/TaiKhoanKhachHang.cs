using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace AspNetCore_WebQuanLyQuanCafe.Models.Domain
{
    public class TaiKhoanKhachHang
    {
        /// <summary>
        /// Gets or sets the ma kh.
        /// </summary>
        /// <value>
        /// The ma kh.
        /// </value>
        [Required]
        public string MaKH { get; set; }

        /// <summary>
        /// Gets or sets the ten kh.
        /// </summary>
        /// <value>
        /// The ten kh.
        /// </value>
        [Required]
        [MaxLength(100)]
        public string TenKH { get; set; }

        /// <summary>
        /// Gets or sets the mat khau.
        /// </summary>
        /// <value>
        /// The mat khau.
        /// </value>
        [Required]
        [MaxLength(30),MinLength(6)]
        public string MatKhau { get; set; }

        /// <summary>
        /// Gets or sets the ngay tao.
        /// </summary>
        /// <value>
        /// The ngay tao.
        /// </value>
        public DateTime NgayTao { get; set; }
    }
}
