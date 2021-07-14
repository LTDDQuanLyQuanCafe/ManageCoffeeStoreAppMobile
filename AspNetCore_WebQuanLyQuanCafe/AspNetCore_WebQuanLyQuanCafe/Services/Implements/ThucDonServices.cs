using AspNetCore_WebQuanLyQuanCafe.Models.ConnectSQL;
using AspNetCore_WebQuanLyQuanCafe.Models.Domain;
using AspNetCore_WebQuanLyQuanCafe.Services.Contracts;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Threading.Tasks;

namespace AspNetCore_WebQuanLyQuanCafe.Services.Implements
{
    public class ThucDonServices : IThucDonServices
    {

        private readonly SqlConnectDB _sqlConnectDB;
        public ThucDonServices(SqlConnectDB sqlConnectDB)
        {
            _sqlConnectDB = sqlConnectDB;
        }

        /// <summary>
        /// Gets all thuc don.
        /// </summary>
        /// <returns><br/></returns>
        public async Task<IList<ThucDon>> GetAllThucDon()
        {
            try
            {
                await _sqlConnectDB.OpenAsync();

                var queryThucDons = "select * from ThucDon";
                SqlCommand cmdThucDon = new SqlCommand(queryThucDons, _sqlConnectDB.sqlConnection);
                SqlDataReader rdThucDon = cmdThucDon.ExecuteReader();

                //Get list 
                var result = new List<ThucDon>();
                while (rdThucDon.Read())
                {
                    result.Add(new ThucDon()
                    {   
                        MaThucDon = int.Parse(rdThucDon[0].ToString()),
                        TenMon = rdThucDon[1].ToString(),
                        DonGia = double.Parse(rdThucDon[2].ToString()),
                        DVT = rdThucDon[3].ToString(),
                        HinhAnh = rdThucDon[4].ToString(),
                        MoTa = rdThucDon[5].ToString(),
                        MaLoaiTD = rdThucDon[6].ToString(),
                    });
                }

                await _sqlConnectDB.CloseAsync();
                return result;
            }
            catch
            {
                return null;
            }
        }

    }
}
