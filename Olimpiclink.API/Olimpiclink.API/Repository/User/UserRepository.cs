using Microsoft.EntityFrameworkCore;
using Olimpiclink.API.Data;
using Olimpiclink.API.Dto;
using Olimpiclink.API.Models;
using Olimpiclink.API.Service;

namespace Olimpiclink.API.Repository.User
{
    public class UserRepository : IUserRepository
    {
        private readonly AppDbContext _context;
        public UserRepository(AppDbContext context)
        {
            _context = context;
        }

        public async Task<ResponseModel<UserModel>> CreateUser(UserCreateDto user)
        {
            ResponseModel<UserModel> response = new ResponseModel<UserModel>();
            var existingEmail =  await _context.Users.FirstOrDefaultAsync(u => u.Email == user.Email);
            var existingUsername = await _context.Users.FirstOrDefaultAsync(u => u.UserName == user.UserName);
            if(existingEmail != null)
            {
                response.Message = "Email";
                response.Status = false;
                return response;
            }
            else if (existingUsername != null)
            {
                response.Message = "Username";
                response.Status = false;
                return response;
            }
            try
            {
                UserModel newUser = new UserModel()
                {
                    Name = user.Name,
                    Email = user.Email,
                    Password = user.Password,
                    UserName = user.UserName,
                };
                await _context.Users.AddAsync(newUser);
                await _context.SaveChangesAsync();
                response.Value = newUser;
                response.Message = "Sucesso";
            }
            catch (Exception ex)
            {
                response.Message = ex.Message;
                response.Status = false;
            }
            return response;
        }

        public Task<ResponseModel<UserModel>> DeleteUser(UserModel user)
        {
            throw new NotImplementedException();
        }

        public Task<ResponseModel<UserModel>> GetUserById(int id)
        {
            throw new NotImplementedException();
        }

        public async Task<ResponseModel<List<UserModel>>> ListUsers()
        {
            ResponseModel<List<UserModel>> response = new ResponseModel<List<UserModel>>();
            try
            {
                response.Value = await _context.Users.ToListAsync();
                response.Status = true;
                response.Message = "Sucesso";
            }
            catch(Exception ex)
            {
                response.Message = ex.Message;
            }
            return response;
        }

        public async Task<ResponseModel<UserModel>> LoginUser(string username, string password)
        {
            ResponseModel<UserModel> response = new ResponseModel<UserModel>();
            try
            {
                var user = await _context.Users.FirstOrDefaultAsync(user => user.UserName == username && user.Password == password);
                if(user == null)
                {
                    response.Message = "Usuário ou senha inválidos";
                    return response;
                }
                var token = TokenService.GenerateToken(user);
                user.Password = token;
                response.Value = user;
                response.Message = "Sucesso";
                response.Status = true;
            }
            catch(Exception ex)
            {

            }
            return response;
        }

        public Task<ResponseModel<UserModel>> UpdateUser(UserModel user)
        {
            throw new NotImplementedException();
        }
    }
}
