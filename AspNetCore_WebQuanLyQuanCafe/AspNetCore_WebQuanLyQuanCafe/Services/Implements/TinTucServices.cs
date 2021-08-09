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
    public class TinTucServices : ITinTucServices
    {
        private readonly SqlConnectDB _sqlConnectDB;

        public TinTucServices(SqlConnectDB sqlConnectDB)
        {
            _sqlConnectDB = sqlConnectDB;
        }

        /// <summary>
        /// Get all tin tucs
        /// </summary>
        /// <returns></returns>
        public async Task<List<TinTuc>> GetAllTinTucs()
        {
            try
            {
                await _sqlConnectDB.OpenAsync();
                var tinTucs = new List<TinTuc>();
                var queryTinTucs = "select * from TinTuc";
                SqlCommand cmdTinTuc = new SqlCommand(queryTinTucs, _sqlConnectDB.sqlConnection);
                SqlDataReader rdTinTuc = cmdTinTuc.ExecuteReader();

                while (rdTinTuc.Read())
                {
                    tinTucs.Add(new TinTuc()
                    {
                        MaTinTuc = int.Parse(rdTinTuc[0].ToString()),
                        HinhAnh = rdTinTuc[1].ToString(),
                        TieuDe = rdTinTuc[2].ToString(),
                        NoiDung = rdTinTuc[3].ToString()
                    });
                }

                await _sqlConnectDB.CloseAsync();
                return tinTucs;
            }
            catch
            {
                return null;
            }
        }

        /// <summary>
        /// Get tin tuc details
        /// </summary>
        /// <returns></returns>
        public async Task<TinTuc> GetTinTucDetails(int idNew)
        {
            try
            {
                await _sqlConnectDB.OpenAsync();
                var tinTuc = new TinTuc();
                var queryTinTucs = "select * from TinTuc " +
                                    "where MaTinTuc = "+idNew;
                SqlCommand cmdTinTuc = new SqlCommand(queryTinTucs, _sqlConnectDB.sqlConnection);
                SqlDataReader rdTinTuc = cmdTinTuc.ExecuteReader();

                while (rdTinTuc.Read())
                {
                    tinTuc =new TinTuc()
                    {
                        MaTinTuc = int.Parse(rdTinTuc[0].ToString()),
                        HinhAnh = rdTinTuc[1].ToString(),
                        TieuDe = rdTinTuc[2].ToString(),
                        NoiDung = rdTinTuc[3].ToString()
                    };
                }

                await _sqlConnectDB.CloseAsync();
                return tinTuc;
            }
            catch
            {
                return null;
            }
        }
    }
}
