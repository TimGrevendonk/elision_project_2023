resource "aws_vpc" "PrivateVPC" {
  cidr_block = "192.168.0.0/16"
}

# Private subnet
resource "aws_subnet" "PrivateSubnet" {
  vpc_id     = aws_vpc.PrivateVPC.id
  cidr_block = "192.168.1.0/24"
}

resource "aws_internet_gateway" "InternetGateway" {
  vpc_id = aws_vpc.PrivateVPC.id
}

resource "aws_security_group" "rds_sg" {
  name       = "rds_sg"
  vpc_id     = aws_vpc.PrivateVPC.id

  ingress {
    from_port = "3306"
    to_port = "3306"
    protocol = "tcp"
  }

  tags = {
    Name = "rds_sg"
  }
}

resource "aws_nat_gateway" "NATGateway" {
  connectivity_type = "private"
  subnet_id         = aws_subnet.PrivateSubnet.id
}

# Public IP address
resource "aws_eip" "PublicIPAddresss" {
  vpc      = true
}

resource "aws_elastic_beanstalk_application" "development4" {
  name        = "development4"
  description = "development4"
}

resource "aws_elastic_beanstalk_environment" "development4-env" {
  name                = "development4-env"
  application         = aws_elastic_beanstalk_application.development4.name
  solution_stack_name = "64bit Amazon Linux 2 v3.5.3 running Docker"

  setting {
      namespace = "aws:autoscaling:launchconfiguration"
      name = "IamInstanceProfile"
      value = "LabInstanceProfile"
  }
}

resource "aws_elastic_beanstalk_application" "development5" {
  name        = "development5"
  description = "development5"
}

resource "aws_elastic_beanstalk_environment" "development5-env" {
  name                = "development5-env"
  application         = aws_elastic_beanstalk_application.development5.name
  solution_stack_name = "64bit Amazon Linux 2 v3.5.3 running Docker"

  setting {
      namespace = "aws:autoscaling:launchconfiguration"
      name = "IamInstanceProfile"
      value = "LabInstanceProfile"
  }
}

resource "aws_elastic_beanstalk_application" "development6" {
  name        = "development6"
  description = "development6"
}

resource "aws_elastic_beanstalk_environment" "development6-env" {
  name                = "development6-env"
  application         = aws_elastic_beanstalk_application.development6.name
  solution_stack_name = "64bit Amazon Linux 2 v3.5.3 running Docker"

  setting {
      namespace = "aws:autoscaling:launchconfiguration"
      name = "IamInstanceProfile"
      value = "LabInstanceProfile"
  }
  
  # autoscaling
  setting {
      namespace = "aws:autoscaling:launchconfiguration"
      name      = "EC2KeyName"
      value     = "vockey"
  }

  setting {
      namespace = "aws:autoscaling:asg"
      name      = "MinSize"
      value     = "2"
  }

  setting {
      namespace = "aws:autoscaling:asg"
      name      = "MaxSize"
      value     = "5"
  }
  
  setting {
      namespace = "aws:autoscaling:launchconfiguration"
      name      = "IamInstanceProfile"
      value     = "LabInstanceProfile"
  }

  ## Database settings
  # werkt misschien
  setting {
      namespace = "aws:rds:dbinstance"
      name      = "HasCoupledDatabase"
      value     = "true"
  }

  setting {
      namespace = "aws:rds:dbinstance"
      name      = "DBEngine"
      value     = "mysql"
  }

  setting {
      namespace = "aws:rds:dbinstance"
      name      = "DBEngineVersion"
      value     = "8.0.28"
  }

  setting {
      namespace = "aws:rds:dbinstance"
      name      = "DBInstanceClass"
      value     = "db.t2.micro"
  }
  
  setting {
      namespace = "aws:rds:dbinstance"
      name      = "DBUser"
      value     = var.db_username
  }

  setting {
      namespace = "aws:rds:dbinstance"
      name      = "DBPassword"
      value     = var.db_secret
  }

  setting {
      namespace = "aws:rds:dbinstance"
      name      = "DBAllocatedStorage"
      value     = "5"
  }

  setting {
      namespace = "aws:rds:dbinstance"
      name      = "DBDeletionPolicy"
      value     = "Snapshot"
  }
}

# EC2 encryption
# https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/kms_key
resource "aws_kms_key" "EC2EncryptionKey" {
  description             = "EC2 Encryption Key"
  deletion_window_in_days = 30
}

# https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/ebs_default_kms_key
resource "aws_ebs_default_kms_key" "EC2DefaultEncryptionKey" {
  key_arn = aws_kms_key.EC2EncryptionKey.arn
}

# https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/ebs_encryption_by_default
resource "aws_ebs_encryption_by_default" "EBSEncryption" {
  enabled = true
}


# EC2 VPC manual
# EB VPC manual
# RDS VPC manual
# S3 VPC manual
# AWS SNS manual
# AWS cloudwatch manual
# encryption uitzoeken
# s3 encryption manual
# https://bluexp.netapp.com/blog/amazon-s3-encryption-how-to-protect-your-data-in-s3
# rds encryption manueel
# https://cloudkul.com/blog/how-to-encrypt-aws-rds-database/
# AWS WAF manual
# aws budgets manual
# budget per month > $190
# 1 VPC budget per month > $45
# 2 EC2 budget per month > $37
# 3 ELB budget per month > $34
# 4 WAF budget per month > $31
# 5 RDS budget per month > $27

