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
    public class LoaiTDServices : ILoaiTDServices
    {
        private readonly SqlConnectDB _sqlConnectDB;

        public LoaiTDServices(SqlConnectDB sqlConnectDB)
        {
            _sqlConnectDB = sqlConnectDB;
        }

        /// <summary>
        ///   Get all Tai khoan khach hang
        /// </summary>
        public async Task<IList<LoaiTD>> GetAllLoaiTD()
        {
            try
            {
                await _sqlConnectDB.OpenAsync();

                var queryLoaiTDs = "select * from LOAITHUCDON";
                SqlCommand cmdLoaiTD = new SqlCommand(queryLoaiTDs, _sqlConnectDB.sqlConnection);
                SqlDataReader rdLoaiTD = cmdLoaiTD.ExecuteReader();

                //Get list 
                var result = new List<LoaiTD>();
                while (rdLoaiTD.Read())
                {
                    result.Add(new LoaiTD()
                    {
                        MALOAITD = rdLoaiTD[0].ToString(),
                        TENLOAITD = rdLoaiTD[1].ToString()
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
