using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace AspNetCore_WebQuanLyQuanCafe.Models.Domain
{
    public class LoaiTD
    {
        /// <summary>
        /// Gets or sets the ten kh.
        /// </summary>
        /// <value>
        /// The ten kh.
        /// </value>
        [Required]
        [MaxLength(100)]
        public string MALOAITD { get; set; }

        /// <summary>
        /// Gets or sets the mat khau.
        /// </summary>
        /// <value>
        /// The mat khau.
        /// </value>
        [Required]
        public string TENLOAITD { get; set; }

        /// <summary>
        /// Gets or sets the mat khau.
        /// </summary>
        /// <value>
        /// The mat khau.
        /// </value>
        public int SOLUONGMON { get; set; }
    }
}
