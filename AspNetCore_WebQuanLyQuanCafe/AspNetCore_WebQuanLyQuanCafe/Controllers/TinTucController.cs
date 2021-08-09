using AspNetCore_WebQuanLyQuanCafe.Services.Contracts;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace AspNetCore_WebQuanLyQuanCafe.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class TinTucController : ControllerBase
    {
        private readonly ITinTucServices _tinTucServices;
        public TinTucController(ITinTucServices tinTucServices)
        {
            _tinTucServices = tinTucServices;
        }

        // GET: get/all
        [HttpGet]
        [Route("all")]
        public IActionResult GetBillFollowUser()
        {
            var tinTucs = _tinTucServices.GetAllTinTucs();
            return Ok(tinTucs.Result);
        }

        // GET: details/idNew
        [HttpGet]
        [Route("details/{idNew}")]
        public IActionResult GetTinTucDetails(int idNew)
        {
            var tinTucs = _tinTucServices.GetTinTucDetails(idNew);
            return Ok(tinTucs.Result);
        }
    }
}
