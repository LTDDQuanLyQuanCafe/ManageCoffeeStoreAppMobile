namespace AspNetCore_WebQuanLyQuanCafe.Models.Domain
{
    /// <summary>
    /// Model ThucDon
    /// </summary>
    public class ThucDon
    {
        /// <summary>
        /// Gets or sets the ma thuc don.
        /// </summary>
        /// <value>
        /// The ma thuc don.
        /// </value>
        public int MaThucDon { get; set; }

        /// <summary>
        /// Gets or sets the ten mon.
        /// </summary>
        /// <value>
        /// The ten mon.
        /// </value>
        public string TenMon { get; set; }

        /// <summary>
        /// Gets or sets the don gia.
        /// </summary>
        /// <value>
        /// The don gia.
        /// </value>
        public double DonGia { get; set; }

        /// <summary>
        /// Gets or sets the DVT.
        /// </summary>
        /// <value>
        /// The DVT.
        /// </value>
        public string DVT { get; set; }

        /// <summary>
        /// Gets or sets the hinh anh.
        /// </summary>
        /// <value>
        /// The hinh anh.
        /// </value>
        public string HinhAnh { get; set; }

        /// <summary>
        /// Gets or sets the mo ta.
        /// </summary>
        /// <value>
        /// The mo ta.
        /// </value>
        public string MoTa { get; set; }

        /// <summary>
        /// Gets or sets the ma loai td.
        /// </summary>
        /// <value>
        /// The ma loai td.
        /// </value>
        public string MaLoaiTD { get; set; }
    }
}
