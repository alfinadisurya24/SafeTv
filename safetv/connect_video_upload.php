<?php 
class DB{
    private $dbHost = "localhost";
    private $dbUsername = "root";
    private $dbPassword = "";
    private $dbName = "safetv";

    public $db_con;

    public function __construct() { 
        if (!isset($this->db)) {
        try {
         $pdo = new PDO("mysql:host".$this->dbHost."; dbname=".$this->dbName, $this->dbUsername, $this->dbPassword);
         $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
         $this->db_con = $pdo;   
        } catch (PDOException $e) {
            die("Failed to connect with mysql :". $e->getMessage());
        }
    }    
    }

    public function addImageToDB(){
        $sql = "INSERT INTO video_user (kategori, judul, thumbnail, user_id) VALUES (:kategori, :judul, :thumbnail, :user_id)";
        $stmt->$this->db_con->prepare($sql);
        $stmt->bindParam(":kategori", $kategori);
        $stmt->bindParam(":judul", $judul);
        $stmt->bindParam(":thumbnail", $thumbnail);
        $stmt->bindParam(":thumbnail", $user_id);
        $stmt->execute();
        $newId=$this->db_con->lastInsertId();
        return $newId; 
    }
}

?>